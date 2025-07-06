-- docker/init.sql
-- 도커로 DB를 컨테이너로 띄울 때 자동으로 초기화 작업 실행

CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(255),
                      name VARCHAR(50) NOT NULL,
                      role VARCHAR(20) NOT NULL DEFAULT 'USER',
                      university VARCHAR(100) DEFAULT '을지대학교',
                      major VARCHAR(100),
                      grade INT CHECK (grade BETWEEN 1 AND 4),
                      interests TEXT,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- major_keyword: 전공별 키워드
-- 전공 키워드 테이블





CREATE TABLE region (
                        code VARCHAR(4) PRIMARY KEY COMMENT '지역코드',
                        name VARCHAR(20) NOT NULL COMMENT '지역명'
);

INSERT INTO region (code, name) VALUES
                                    ('0101', '서울'),
                                    ('0102', '부산'),
                                    ('0103', '대구'),
                                    ('0104', '인천'),
                                    ('0105', '광주'),
                                    ('0106', '대전'),
                                    ('0107', '울산'),
                                    ('0117', '세종'),
                                    ('0108', '경기'),
                                    ('0109', '강원'),
                                    ('0110', '충북'),
                                    ('0111', '충남'),
                                    ('0112', '전북'),
                                    ('0113', '전남'),
                                    ('0114', '경북'),
                                    ('0115', '경남'),
                                    ('0116', '제주');

use camjob;

INSERT INTO Major (name) VALUES
                             ('홍보디자인학과'),
                             ('간호학과'),
                             ('아동청소년학과'),
                             ('장례지도학과'),
                             ('응급구조학과'),
                             ('물리치료학과'),
                             ('치위생학과'),
                             ('스포츠아웃도어학과'),
                             ('중독재활복지학과');

-- 홍보디자인학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (1, '디자인'), (1, '홍보'), (1, '포스터'), (1, '일러스트'),
                                                 (1, 'SNS'), (1, '브랜딩'), (1, 'UX'), (1, 'UI'), (1, '카드뉴스'), (1, '영상편집');

-- 간호학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (2, '간호'), (2, '건강'), (2, '환자'), (2, '의료'),
                                                 (2, '응급'), (2, '복약지도'), (2, '간호사'), (2, '예방접종'), (2, '감염관리'), (2, '의료봉사');

-- 아동청소년학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (3, '아동'), (3, '청소년'), (3, '놀이'), (3, '멘토링'),
                                                 (3, '교육'), (3, '보육'), (3, '심리'), (3, '유아'), (3, '방과후'), (3, '상담');

-- 장례지도학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (4, '장례'), (4, '고인'), (4, '장례식장'), (4, '유골'),
                                                 (4, '의전'), (4, '화장'), (4, '조문'), (4, '부고'), (4, '납골당'), (4, '추모');

-- 응급구조학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (5, '응급'), (5, '심폐소생술'), (5, '구조'), (5, 'CPR'),
                                                 (5, '소방'), (5, 'AED'), (5, '재난'), (5, '출동'), (5, '응급처치'), (5, '의료지원');

-- 물리치료학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (6, '물리치료'), (6, '재활'), (6, '운동치료'), (6, '근력강화'),
                                                 (6, '척추'), (6, '마사지'), (6, '회복'), (6, '자세교정'), (6, '유연성'), (6, '보행');

-- 치위생학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (7, '치과'), (7, '치위생'), (7, '스케일링'), (7, '치아'),
                                                 (7, '구강보건'), (7, '칫솔질'), (7, '구강검진'), (7, '잇몸'), (7, '불소'), (7, '예방');

-- 스포츠아웃도어학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (8, '스포츠'), (8, '야외활동'), (8, '캠핑'), (8, '등산'),
                                                 (8, '체육'), (8, '운동'), (8, '플로깅'), (8, '야외교육'), (8, '레크리에이션'), (8, '트래킹');

-- 중독재활복지학과 키워드
INSERT INTO major_keyword (major_id, keyword) VALUES
                                                 (9, '중독'), (9, '재활'), (9, '알코올'), (9, '약물'),
                                                 (9, '인터넷중독'), (9, '심리상담'), (9, '회복'), (9, '정신건강'), (9, '금주'), (9, '금연');
