-- Clear tables
DELETE FROM exercise_set CASCASDE;
DELETE FROM workout_exercise CASCASDE;
DELETE FROM exercise CASCASDE;
DELETE FROM workout CASCASDE;

DELETE FROM users_roles CASCADE;
DELETE FROM user_profile CASCADE;
DELETE FROM users CASCADE;
DELETE FROM roles CASCADE;

-- Insert dummy user data
INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER')
ON CONFLICT DO NOTHING;

INSERT INTO users (id, username, password)
VALUES (1, 'user', '$2a$12$TKtG/C3W/QjaPqm.mvhlGeb0teOFPMvSbgP9LuRxDLdSEeH7zv5Oe')
ON CONFLICT DO NOTHING;

INSERT INTO user_profile (id, username, length, weight)
VALUES (1, 'user', 178, 63)
ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1)
ON CONFLICT DO NOTHING;

-- Insert dummy workout data
INSERT INTO exercise (id, name, type)
VALUES (1, 'Incline Chest Press', 'WEIGHT'),
       (2, 'Pull Ups', 'BODY_WEIGHT')
ON CONFLICT DO NOTHING;

INSERT INTO workout (id, date, name, user_profile_id)
VALUES (1, CURRENT_DATE, 'Chest Workout', 1)
ON CONFLICT DO NOTHING;

INSERT INTO workout_exercise (id, exercise_id, workout_id)
VALUES (1, 1, 1)
ON CONFLICT DO NOTHING;

INSERT INTO exercise_set (reps, time, weight, workout_exercise_id)
VALUES (7, 0, 27.5, 1),
       (6, 0, 25, 1),
       (10, 0, 20, 1)
ON CONFLICT DO NOTHING;

