CREATE TABLE payments(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `value` DECIMAL(19,2) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `number` VARCHAR(19) NOT NULL,
    `expiration` VARCHAR(7) NOT NULL,
    `code` VARCHAR(3) NOT NULL,
    `status` VARCHAR(255) NOT NULL,
    `form_of_payment_id` BIGINT(20) NOT NULL,
    `order_id` BIGINT(20) NOT NULL,
    PRIMARY KEY(id)
)