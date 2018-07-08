INSERT INTO REMINDERS (REMINDER_ID, NAME, DESCRIPTION, DUE_DATE, STATUS, VERSION) 
VALUES 
	(NEXTVAL('REMINDERS_SEQ'), 'Finish first version', '', '2018-07-07', 'DONE', 0),
	(NEXTVAL('REMINDERS_SEQ'), 'Add Swagger-UI', '', '2018-07-07', 'NOTDONE', 0),
	(NEXTVAL('REMINDERS_SEQ'), 'Send e-mail with repository link', '', '2018-07-12', 'NOTDONE', 0),
	(NEXTVAL('REMINDERS_SEQ'), 'Wash clothes', '', '2018-07-07', 'DONE', 0),
	(NEXTVAL('REMINDERS_SEQ'), 'Finish barbeque invitations', '', '2018-07-09', 'NOTDONE', 0);

