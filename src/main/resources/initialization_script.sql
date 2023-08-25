INSERT INTO users
(username, "password", enabled)
VALUES('admin', '$2a$12$Bm2EgKw2WZ5T4lpagVi1iOxWZmla76Z0C0jcLCP4q7v4hDsZ2heMS', true) ON CONFLICT DO NOTHING;

INSERT INTO company
(id, company_name, company_description, slogan, address, contact_email, nif, stat, rcs, logo)
VALUES
    ('1', 'ABC Corporation', 'A global leader in innovation', 'Innovate for a brighter future', '123 Main St, Cityville', 'contact@abccorp.com', '123456789', '987654123', '987654', NULL) ON CONFLICT DO NOTHING;
INSERT INTO phone
(id, "number", employee_id, company_id, country_code)
VALUES('company_phone_id1', '0384550518', NULL, '1', '261')ON CONFLICT DO NOTHING;;