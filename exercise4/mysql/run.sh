#!/usr/bin/env bash
echo 'Running mysql_install_db ...'
mysql_install_db --datadir=/var/mysqldata
echo 'Finished mysql_install_db'

cat > /init-file.sql <<-EOSQL
    SET @@SESSION.SQL_LOG_BIN=0;
    DELETE FROM mysql.user ;
    CREATE USER 'admin'@'%' IDENTIFIED BY 'test';
    GRANT ALL ON *.* TO 'admin'@'%' WITH GRANT OPTION;
    DROP DATABASE IF EXISTS test ;
    FLUSH PRIVILEGES;
    CREATE DATABASE IF NOT EXISTS sms;
    USE sms;
    CREATE TABLE IF NOT EXISTS messages ( id BIGINT(10) AUTO_INCREMENT PRIMARY KEY, message VARCHAR(255), created TIMESTAMP);
EOSQL
echo 'Starting MySQL server'
mysqld --init-file /init-file.sql --gdb --datadir=/var/mysqldata --skip-name-resolve
