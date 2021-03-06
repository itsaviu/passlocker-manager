create TABLE folders (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR (200) NOT NULL,
  parent_id BIGINT,
  user_detail_id BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at  TIMESTAMP NOT NULL
);

CREATE TABLE vault (
  id BIGSERIAL PRIMARY KEY,
  user_detail_id BIGINT NOT NULL,
  folder_id BIGINT NOT NULL,
  name VARCHAR (200) NOT NULL,
  login VARCHAR (200) NOT NULL,
  credentials TEXT NOT NULL,
  url TEXT NOT NULL,
  pass_type VARCHAR(200)NOT NULL,
  notes TEXT,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL
);


ALTER TABLE folders
  ADD CONSTRAINT FOLDERS_USER_DETIAL_FK FOREIGN KEY (user_detail_id) REFERENCES user_details(id);

ALTER TABLE folders
  ADD CONSTRAINT FOLDERS_FOLDERS_PARENT_ID_FK FOREIGN KEY (parent_id) REFERENCES folders(id);

ALTER TABLE vault
  ADD CONSTRAINT VAULT_FOLDERS_FK FOREIGN KEY (folder_id) REFERENCES folders(id);


ALTER TABLE vault
  ADD CONSTRAINT VAULT_USER_DETAIL_FK FOREIGN KEY (user_detail_id) REFERENCES user_details(id);


