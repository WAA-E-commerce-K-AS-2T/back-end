# back-end
make for studying


Create database

docker run --name productdb -p 6000:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=productdb -e MYSQL_USER=productuser -e MYSQL_PASSWORD=productpass -d mysql:latest


“back-end” open with Intellij Idea then run

“Front-end/app” open with VS code then run below commands

npm install

npm start



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