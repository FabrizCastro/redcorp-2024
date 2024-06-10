INSERT INTO sections (section_name, section_description) VALUES ('Marketing y Ventas', 'Descripción de Ventas');
INSERT INTO sections (section_name, section_description) VALUES ('Recursos Humanos', 'Descripción de Recursos Humanos');
INSERT INTO sections (section_name, section_description) VALUES ('Finanzas y Contabilidad', 'Descripción de Finanzas');
INSERT INTO sections (section_name, section_description) VALUES ('Area de Sistemas', 'Descripción de Sistemas');
INSERT INTO sections (section_name, section_description) VALUES ('Producción', 'Descripción de Operaciones');

INSERT INTO teams (team_name, team_description, section_id) VALUES ('Los cocineros','Equipo especializado en Backend',4);
INSERT INTO teams (team_name, team_description, section_id) VALUES ('Los meseros','Equipo que realiza los servicios API',5);
INSERT INTO teams (team_name, team_description, section_id) VALUES ('Los obreros','Equipo encargado del Frontend',4);
INSERT INTO teams (team_name, team_description, section_id) VALUES ('Los reclutadores','Equipo encargado de reclutar profesionales',2);
INSERT INTO teams (team_name, team_description, section_id) VALUES ('Los vendedores','Equipo encargado del Marketing de la empresa',1);
INSERT INTO teams (team_name, team_description, section_id) VALUES ('Los arquitectos','Equipo asignado a la adecuación del Frontend',4);
INSERT INTO teams (team_name, team_description, section_id) VALUES ('Los productores','Equipo asignado a la operación del negocio',5);

INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Alpha', 'Initial phase of the Alpha project', '2024-01-01', '2024-06-30', 'In Progress', 1);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Beta', 'Development of Beta functionalities', '2024-02-15', '2024-12-31', 'To Do', 2);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Gamma', 'Research for Gamma project', '2024-03-01', '2024-08-31', 'Completed', 3);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Delta', 'Implementation of Delta system', '2024-04-01', '2024-09-30', 'In Revision', 3);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Epsilon', 'Testing phase of Epsilon', '2024-05-01', '2024-10-31', 'In Progress', 5);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Zeta', 'Deployment of Zeta features', '2024-06-01', '2024-11-30', 'Completed', 6);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Eta', 'Evaluation of Eta project outcomes', '2024-07-01', '2024-12-31', 'In Progress', 7);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Theta', 'Theta project initiation', '2024-08-01', '2025-01-31', 'To Do', 2);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Iota', 'Analysis for Iota', '2024-09-01', '2025-02-28', 'In Revision', 1);
INSERT INTO projects (project_name, project_description, project_initial_date, project_final_date, project_state, team_id) VALUES ('Project Kappa', 'Development of Kappa solutions', '2024-10-01', '2025-03-31', 'In Progress', 10);
