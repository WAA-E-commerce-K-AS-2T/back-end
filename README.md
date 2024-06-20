# back-end
make for studying


Expired JWT token

eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImJ1eWVyQGV4YW1wbGUuY29tIiwic3ViIjoiYnV5ZXJAZXhhbXBsZS5jb20iLCJpYXQiOjE3MTg4NDY4ODMsImV4cCI6MTcxODg1MDQ4M30.0Ek36PrKSl3b8dpVgOO5AeDt9oBXa37hTUzSbPx9YOI



-- BUYER ROLES
SELECT p.*, r.name FROM privilege p
inner join roles_privileges rp ON p.id = rp.privilege_id
inner join role r ON rp.role_id = r.id
where role_id=4;


--SELLER ROLES
SELECT p.*, r.name FROM privilege p
inner join roles_privileges rp ON p.id = rp.privilege_id
inner join role r ON rp.role_id = r.id
where role_id=3;