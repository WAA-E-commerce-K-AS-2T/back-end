

INSERT INTO role (description, name)
VALUES ('System Admin Role', 'ROLE_SYSTADM'),
       ('This is form just Admin', 'ROLE_ADMIN'),
       ('This is form just Owner', 'ROLE_SELLER'),
       ('This is form just Customer', 'ROLE_BUYER'),
       ('This is form just Viewer', 'ROLE_VIEWER');

INSERT INTO privilege (name)
VALUES ('users.read'),
       ('users.write'),
       ('users.delete'),
       ('privileges.read'),
       ('privileges.write'),
       ('privileges.delete'),
       ('roles.read'),
       ('roles.write'),
       ('roles.delete'),
       ('category.read'),
       ('category.write'),
       ('category.delete'),
       ('product.read'),
       ('product.write'),
       ('product.delete'),
       ('order.read'),
       ('order.write'),
       ('order.delete'),
       ('applications.read'),
       ('applications.write'),
       ('applications.delete'),
       ('roles.delete');

INSERT INTO roles_privileges (privilege_id, role_id)
VALUES (1, 1), (2,1), (3,1), (4,1), (5,1), (6,1), (7,1), (8,1), (9,1), (10,1), (11,1), (12,1);




-- Root categories
INSERT INTO category (name, category_parent) VALUES ('Books', NULL); -- id = 1
INSERT INTO category (name, category_parent) VALUES ('Electronics', NULL); -- id = 2

-- Subcategories for Books
INSERT INTO category (name, category_parent) VALUES ('Fiction', 1); -- id = 3
INSERT INTO category (name, category_parent) VALUES ('Non-Fiction', 1); -- id = 4
INSERT INTO category (name, category_parent) VALUES ('Children\'s Books', 1); -- id = 5

-- Subcategories for Fiction
INSERT INTO category (name, category_parent) VALUES ('Mystery', 3); -- id = 6
INSERT INTO category (name, category_parent) VALUES ('Fantasy', 3); -- id = 7

-- Subcategories for Non-Fiction
INSERT INTO category (name, category_parent) VALUES ('Biography', 4); -- id = 8
INSERT INTO category (name, category_parent) VALUES ('Science', 4); -- id = 9

-- Subcategories for Children's Books
INSERT INTO category (name, category_parent) VALUES ('Picture Books', 5); -- id = 10
INSERT INTO category (name, category_parent) VALUES ('Early Readers', 5); -- id = 11

-- Subcategories for Electronics
INSERT INTO category (name, category_parent) VALUES ('Computers', 2); -- id = 12
INSERT INTO category (name, category_parent) VALUES ('Mobile Phones', 2); -- id = 13

-- Subcategories for Computers
INSERT INTO category (name, category_parent) VALUES ('Laptops', 12); -- id = 14
INSERT INTO category (name, category_parent) VALUES ('Desktops', 12); -- id = 15


