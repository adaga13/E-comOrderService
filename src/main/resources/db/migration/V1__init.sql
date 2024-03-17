CREATE TABLE `order_table`
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NULL,
    user_id         BIGINT NULL,
    product_id      BIGINT NULL,
    amount          DOUBLE NULL,
    quantity        INT NULL,
    discount        DOUBLE NULL,
    order_date      datetime NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE INDEX product_index ON `order_table` (product_id);

CREATE INDEX user_index ON `order_table` (user_id);