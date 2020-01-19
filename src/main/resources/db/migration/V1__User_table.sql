CREATE TABLE user_details(
  id         BIGSERIAL PRIMARY KEY,
  user_id    BIGINT NOT NULL,
  email_id      VARCHAR(200) NOT NULL,
  username   VARCHAR(200) NOT NULL,
  created_at TIMESTAMP NOT NULL
);
