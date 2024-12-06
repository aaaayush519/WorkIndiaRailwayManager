CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(10) NOT NULL
);

CREATE TABLE train (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       source VARCHAR(255) NOT NULL,
                       destination VARCHAR(255) NOT NULL,
                       total_seats INT NOT NULL,
                       available_seats INT NOT NULL
);

CREATE TABLE booking (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         train_id BIGINT NOT NULL,
                         source VARCHAR(255),
                         destination VARCHAR(255),
                         FOREIGN KEY (user_id) REFERENCES user(id),
                         FOREIGN KEY (train_id) REFERENCES train(id)
);
