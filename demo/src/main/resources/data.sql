INSERT INTO users (name, email, phone) VALUES ('Ankit', 'ankit@example.com', '1234567890');
INSERT INTO user_preferences (user_id, email_opt_in, sms_opt_in, push_opt_in, in_app_opt_in) VALUES (1, TRUE, FALSE, TRUE, TRUE);
-- Notice SMS is FALSE. This will test our filtering logic later.