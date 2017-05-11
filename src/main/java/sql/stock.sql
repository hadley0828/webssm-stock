CREATE TABLE `stock` (
  `stock_serial` varchar(64) NOT NULL,
  `stock_date` date NOT NULL,
  `open_price` double NOT NULL,
  `high_price` double NOT NULL,
  `low_price` double NOT NULL,
  `close_price` double NOT NULL,
  `volume` varchar(64) NOT NULL,
  `adj_close` double NOT NULL,
  `stock_code` varchar(10) NOT NULL,
  `stock_name` varchar(64) NOT NULL,
  `market` varchar(6) NOT NULL,
  PRIMARY KEY (`stock_code`,`stock_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

