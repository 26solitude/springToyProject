-- Reset the 'users' table and reset the auto-increment value
TRUNCATE TABLE users RESTART IDENTITY;

-- Insert new data
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('kim_minsoo', '김민수', 'minsoo.kim@example.com', 'http://example.com/minsoo.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('lee_younghee', '이영희', 'younghee.lee@example.com', 'http://example.com/younghee.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('park_jisoo', '박지수', 'jisoo.park@example.com', 'http://example.com/jisoo.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('choi_jinwoo', '최진우', 'jinwoo.choi@example.com', 'http://example.com/jinwoo.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('jang_hyejin', '장혜진', 'hyejin.jang@example.com', 'http://example.com/hyejin.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('kang_dongha', '강동하', 'dongha.kang@example.com', 'http://example.com/dongha.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('yoon_sohee', '윤소희', 'sohee.yoon@example.com', 'http://example.com/sohee.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('han_sungho', '한성호', 'sungho.han@example.com', 'http://example.com/sungho.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('oh_sungmin', '오성민', 'sungmin.oh@example.com', 'http://example.com/sungmin.png');
INSERT INTO users (username, full_name, email, profile_picture) VALUES ('jung_mirae', '정미래', 'mirae.jung@example.com', 'http://example.com/mirae.png');
