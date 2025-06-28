package com.example.camjob.scheduler;

import com.example.camjob.entity.Major;
import com.example.camjob.entity.MajorKeyword;
import com.example.camjob.entity.VolunteerPost;
import com.example.camjob.entity.VolunteerPostMajorMapping;
import com.example.camjob.repository.MajorKeywordRepository;
import com.example.camjob.repository.MajorRepository;
import com.example.camjob.repository.VolunteerPostMajorMappingRepository;
import com.example.camjob.repository.VolunteerPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;


@Component
@RequiredArgsConstructor
@Slf4j
public class VolunteerPostInitalizer implements CommandLineRunner {

    private final VolunteerPostRepository postRepository;
    private final MajorRepository majorRepository;
    private final MajorKeywordRepository keywordRepository;
    private final VolunteerPostMajorMappingRepository mappingRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🔍 봉사 공고 API 호출 테스트 중...");

        // 지역 코드 리스트
//        String[] areaCodes = {
//                "0101", "0102", "0103", "0104", "0105", "0106", "0107", "0117",
//                "0108", "0109", "0110", "0111", "0112", "0113", "0114", "0115", "0116"
//        };
        String[] areaCodes = {
                "0101" // 테스트용으로 서울만
        };
//        for (String areaCode : areaCodes) {
//            fetchAndSavePostsByArea(areaCode);
//        }
        System.out.println("✅ 전체 지역 API 호출 완료");
    }

    private void fetchAndSavePostsByArea(String areaCode) {
        try {
            String serviceKeyEncoded = "r5S29wf6%2FX8NEjP64wd%2F46g9R%2Bsnn3ILxX9kEnvNlVzAd3%2BQNhi4hx80e1awksSwqWIETMYNrrlrVRUshHJDVA%3D%3D";

            String requestUrl = "https://apis.data.go.kr/B460014/vmsdataview/getVollcolectionList"
                    + "?serviceKey=" + serviceKeyEncoded
                    + "&numOfRows=100"
                    + "&pageNo=1"
                    + "&strDate=2025-01-01"
                    + "&endDate=2025-12-31"
                    + "&areaCode=" + areaCode
                    + "&TermType=1"
                    + "&status=1";

            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(requestUrl);
            String response = restTemplate.getForObject(uri, String.class);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builderXml = factory.newDocumentBuilder();
            Document doc = builderXml.parse(new InputSource(new StringReader(response)));

            NodeList items = doc.getElementsByTagName("item");

            for (int i = 0; i < items.getLength(); i++) {
                Element item = (Element) items.item(i);

                String seq = getText(item, "seq");
                String rawTitle = getText(item, "title");
                String title = StringEscapeUtils.unescapeHtml4(rawTitle);
                String org = getText(item, "centName");
                String areaName = getText(item, "areaName");
                String place = getText(item, "place");
                String date = getText(item, "regDate");
                String actType = getText(item, "actTypeName");
                String status = getText(item, "statusName");

                if (postRepository.existsByExternalId(Long.parseLong(seq))) continue;

                VolunteerPost post = new VolunteerPost();
                post.setExternalId(Long.parseLong(seq));
                post.setTitle(title);
                post.setPlace(place);
                post.setDescription(actType);
                post.setOrganization(org);
                post.setLocation(areaName);
                post.setActivityDate(date);
                post.setStatus(status);

                postRepository.save(post);

                // 전공 키워드 매칭
                List<Major> majors = majorRepository.findAll();
                for (Major major : majors) {
                    List<MajorKeyword> keywords = keywordRepository.findByMajor(major);
                    for (MajorKeyword keyword : keywords) {
                        if (Pattern.compile("\\b" + Pattern.quote(keyword.getKeyword()) + "\\b").matcher(title).find()) {
                            VolunteerPostMajorMapping mapping = new VolunteerPostMajorMapping();
                            mapping.setVolunteerPost(post);
                            mapping.setMajor(major);
                            mappingRepository.save(mapping);
                            break; // 해당 전공과 1개라도 매칭되면 연결
                        }
                    }
                }
            }

            System.out.println("✅ " + areaCode + " 지역 데이터 저장 완료 (" + items.getLength() + "건)");

        } catch (Exception e) {
            System.err.println("❌ " + areaCode + " 지역 데이터 저장 실패: " + e.getMessage());
        }
    }

    private String getText(Element item, String tagName) {
        NodeList nodeList = item.getElementsByTagName(tagName);
        return nodeList.getLength() > 0 ? nodeList.item(0).getTextContent() : "";
    }
}
