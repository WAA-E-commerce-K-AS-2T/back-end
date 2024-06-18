

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
       ('productphoto.read'),
       ('productphoto.write'),
       ('productphoto.delete'),
       ('order.read'),
       ('order.write'),
       ('order.cancel'),
       ('order.status'),
       ('order.delete'),
       ('orderitem.read'),
       ('orderitem.write'),
       ('orderitem.delete'),
       ('review.read'),
       ('review.write'),
       ('review.delete'),
       ('shoppingcard.read'),
       ('shoppingcard.write'),
       ('shoppingcard.delete'),
       ('none.');

INSERT INTO roles_privileges (privilege_id, role_id)
VALUES (1,1),
       (2,1),
       (3,1),
       (4,1),
       (5,1),
       (6,1),
       (7,1),
       (8,1),
       (9,1),
       (10,1),
       (11,1),
       (12,1),
       (13,1),
       (14,1),
       (15,1),
       (16,1),
       (17,1),
       (18,1),
       (19,1),
       (20,1),
       (21,1),
       (22,1),
       (23,1),
       (24,1),
       (25,1),
       (26,1),
       (27,1),
       (28,1),
       (29,1);

INSERT INTO roles_privileges (privilege_id, role_id)
VALUES (13, 3),(14, 3),(15, 3),(16, 3),(17, 3),(18, 3),(19, 3)
     ,(21, 3),(22, 3);

INSERT INTO roles_privileges (privilege_id, role_id)
VALUES
    (19, 4), (21, 4), (30, 4), (31, 4), (32, 4);

INSERT INTO user (enabled, created_by, created_date, id, last_modified_by, last_modified_date, email, full_name, password) VALUES (true, null, '2024-06-18 15:20:56.203965', 1, null, '2024-06-18 15:20:56.203965', 'admin@example.com', 'admin', '$2a$10$NKfxB1euNxnPG7.0T2S0k.jTKSGu7uhMMoTaKn4zMMhJbxhReONAa');
INSERT INTO user (enabled, created_by, created_date, id, last_modified_by, last_modified_date, email, full_name, password) VALUES (true, null, '2024-06-18 15:21:00.019446', 2, null, '2024-06-18 15:21:00.019446', 'buyer@example.com', 'buyer', '$2a$10$2bgbBoiM1oGeHnL0awJ4LedVEBw35Cym/x2M8exBz7W36qbL3L.fK');
INSERT INTO user (enabled, created_by, created_date, id, last_modified_by, last_modified_date, email, full_name, password) VALUES (true, null, '2024-06-18 15:20:56.203965', 3, null, '2024-06-18 15:20:56.203965', 'seller@example.com', 'seller', '$2a$10$NKfxB1euNxnPG7.0T2S0k.jTKSGu7uhMMoTaKn4zMMhJbxhReONAa');

INSERT INTO seller (id) VALUES (3);
INSERT INTO buyer (id) VALUES (2);

INSERT INTO productdb.users_roles (id, role_id, user_id) VALUES (1, 1, 1);
INSERT INTO productdb.users_roles (id, role_id, user_id) VALUES (2, 4, 2);
INSERT INTO productdb.users_roles (id, role_id, user_id) VALUES (3, 3, 3);



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


