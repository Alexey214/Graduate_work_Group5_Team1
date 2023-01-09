SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE datname = 'ADS-ONLINE'
  AND pid <> pg_backend_pid();

DROP DATABASE "ADS-ONLINE";

create sequence order_id.hibernate_sequence