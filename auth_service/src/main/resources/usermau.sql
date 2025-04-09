INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');

-- Thêm một người dùng admin (với mật khẩu đã được mã hóa bằng BCrypt - bạn cần thay thế 'your_encoded_password_here' bằng mật khẩu đã được mã hóa)
INSERT INTO user (username, password, enabled) VALUES ('admin', '$2a$10$gudGSx60aHnw3zF8w8NjWOD6AJgcL4junI/RnA7qsFBGloya1r9Ge', TRUE);

-- Thêm một người dùng thông thường (với mật khẩu đã được mã hóa)
INSERT INTO user (username, password, enabled) VALUES ('user', '$2a$10$JOCXUpnIxO6hCfZM9YlQ4eB4At94XBdj1FJOdzJsSvUgWX0DegtJS', TRUE);

-- Gán quyền ADMIN cho người dùng 'admin'
INSERT INTO users_authorities (user_id, authority_id) VALUES (
    (SELECT id FROM user WHERE username = 'admin' LIMIT 1),
    (SELECT id FROM authority WHERE name = 'ROLE_ADMIN' LIMIT 1)
);

-- Gán quyền USER cho người dùng 'user'
INSERT INTO users_authorities (user_id, authority_id) VALUES (
    (SELECT id FROM user WHERE username = 'user' LIMIT 1),
    (SELECT id FROM authority WHERE name = 'ROLE_USER' LIMIT 1)
);