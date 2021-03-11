CREATE TABLE IF NOT EXISTS milkproducts
(
    milkProduct_id        BIGINT         NOT NULL AUTO_INCREMENT,
    name              VARCHAR(20)    NOT NULL,
    price             DECIMAL(10, 2) NOT NULL,
    description       VARCHAR(100)   NOT NULL,
    created           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (milkProduct_id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 0;

CREATE TABLE IF NOT EXISTS cheese
(
    cheese_id         BIGINT NOT NULL AUTO_INCREMENT,
    cheese_type              VARCHAR(20)    NOT NULL,
    milkProduct_id BIGINT NOT NULL,
    PRIMARY KEY (cheese_id),
    KEY milkProduct_id (milkProduct_id),
    FOREIGN KEY (milkProduct_id) references milkproducts (milkProduct_id)

)

CREATE TABLE IF NOT EXISTS yogurt
(
    yogurt_id         BIGINT NOT NULL AUTO_INCREMENT,
    cheese_type              VARCHAR(20)    NOT NULL,
    flavor  VARCHAR(20)    NOT NULL,
    PRIMARY KEY (yogurt_id),
    KEY milkProduct_id (milkProduct_id),
    FOREIGN KEY (milkProduct_id) references milkproducts (milkProduct_id)

)