CREATE TABLE item_request (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `request_id` bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (request_id) REFERENCES requests(id)
)