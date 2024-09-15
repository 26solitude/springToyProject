-- Insert new data
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('kim_minsoo', '김민수', 'minsoo.kim@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/minsoo.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('lee_younghee', '이영희', 'younghee.lee@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/younghee.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('park_jisoo', '박지수', 'jisoo.park@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/jisoo.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('choi_jinwoo', '최진우', 'jinwoo.choi@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/jinwoo.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('jang_hyejin', '장혜진', 'hyejin.jang@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/hyejin.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('kang_dongha', '강동하', 'dongha.kang@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/dongha.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('yoon_sohee', '윤소희', 'sohee.yoon@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/sohee.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('han_sungho', '한성호', 'sungho.han@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/sungho.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('oh_sungmin', '오성민', 'sungmin.oh@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/sungmin.png');
INSERT INTO users (username, full_name, email, password, profile_picture) VALUES ('jung_mirae', '정미래', 'mirae.jung@example.com', '$2a$10$Dow1lOHpKlPbNiLzXK7xe.N5NYfEXz7WmOt1b1xiFs0iKsHKBtBvq', 'http://example.com/mirae.png');

INSERT INTO posts (user_id, image_url, description) VALUES (1, 'http://example.com/post1_minsoo.png', '민수의 첫 번째 게시물입니다.');
INSERT INTO posts (user_id, image_url, description) VALUES (1, 'http://example.com/post2_minsoo.png', '민수의 두 번째 게시물입니다.');
INSERT INTO posts (user_id, image_url, description) VALUES (1, 'http://example.com/post3_minsoo.png', '민수의 세 번째 게시물입니다.');

INSERT INTO posts (user_id, image_url, description) VALUES (2, 'http://example.com/post1_younghee.png', '영희의 첫 번째 게시물입니다.');
INSERT INTO posts (user_id, image_url, description) VALUES (2, 'http://example.com/post2_younghee.png', '영희의 두 번째 게시물입니다.');
INSERT INTO posts (user_id, image_url, description) VALUES (2, 'http://example.com/post3_younghee.png', '영희의 세 번째 게시물입니다.');

INSERT INTO posts (user_id, image_url, description) VALUES (3, 'http://example.com/post1_jisoo.png', '지수의 첫 번째 게시물입니다.');
INSERT INTO posts (user_id, image_url, description) VALUES (3, 'http://example.com/post2_jisoo.png', '지수의 두 번째 게시물입니다.');
INSERT INTO posts (user_id, image_url, description) VALUES (3, 'http://example.com/post3_jisoo.png', '지수의 세 번째 게시물입니다.');

INSERT INTO comments (user_id, post_id, content) VALUES (4, 1, '진우: 멋진 사진이네요!');
INSERT INTO comments (user_id, post_id, content) VALUES (5, 1, '혜진: 정말 멋있어요!');
INSERT INTO comments (user_id, post_id, content) VALUES (6, 1, '동하: 잘 봤어요!');
INSERT INTO comments (user_id, post_id, content) VALUES (7, 1, '소희: 좋아요!');
INSERT INTO comments (user_id, post_id, content) VALUES (8, 1, '성호: 최고에요!');

INSERT INTO comments (user_id, post_id, content) VALUES (4, 4, '진우: 또 다른 멋진 사진!');
INSERT INTO comments (user_id, post_id, content) VALUES (5, 4, '혜진: 정말 예쁘네요!');
INSERT INTO comments (user_id, post_id, content) VALUES (6, 4, '동하: 잘 찍었어요!');
INSERT INTO comments (user_id, post_id, content) VALUES (7, 4, '소희: 대단해요!');
INSERT INTO comments (user_id, post_id, content) VALUES (8, 4, '성호: 멋져요!');

INSERT INTO comments (user_id, post_id, content) VALUES (4, 7, '진우: 훌륭합니다!');
INSERT INTO comments (user_id, post_id, content) VALUES (5, 7, '혜진: 완전 좋아요!');
INSERT INTO comments (user_id, post_id, content) VALUES (6, 7, '동하: 감동적이에요!');
INSERT INTO comments (user_id, post_id, content) VALUES (7, 7, '소희: 인상적이네요!');
INSERT INTO comments (user_id, post_id, content) VALUES (8, 7, '성호: 놀랍네요!');