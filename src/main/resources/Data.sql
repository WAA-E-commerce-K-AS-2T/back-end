

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
       ('shoppingcart.read'),
       ('shoppingcart.write'),
       ('shoppingcart.delete'),
       ('profile.read'),
       ('profile.write'),
       ('product.approval'),
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
       (29,1),
       (35,1);

INSERT INTO roles_privileges (privilege_id, role_id)
VALUES (10, 3),(13, 3),(14, 3),(15, 3),(16, 3),(17, 3),(18, 3),(19, 3)
     ,(21, 3),(22, 3), (33, 3), (34, 3);

INSERT INTO roles_privileges (privilege_id, role_id)
VALUES
    (10, 3),(19, 4), (21, 4), (27, 4), (28, 4), (29, 4), (30, 4), (31, 4), (32, 4), (33, 4), (34, 4);

INSERT INTO user (enabled, created_by, created_date, id, last_modified_by, last_modified_date, email, full_name, password) VALUES (true, null, '2024-06-18 15:20:56.203965', 1, null, '2024-06-18 15:20:56.203965', 'admin@example.com', 'admin', '$2a$10$NKfxB1euNxnPG7.0T2S0k.jTKSGu7uhMMoTaKn4zMMhJbxhReONAa');
INSERT INTO user (enabled, created_by, created_date, id, last_modified_by, last_modified_date, email, full_name, password) VALUES (true, null, '2024-06-18 15:21:00.019446', 2, null, '2024-06-18 15:21:00.019446', 'buyer@example.com', 'buyer', '$2a$10$2bgbBoiM1oGeHnL0awJ4LedVEBw35Cym/x2M8exBz7W36qbL3L.fK');
INSERT INTO user (enabled, created_by, created_date, id, last_modified_by, last_modified_date, email, full_name, password) VALUES (true, null, '2024-06-18 15:20:56.203965', 3, null, '2024-06-18 15:20:56.203965', 'seller@example.com', 'seller', '$2a$10$NKfxB1euNxnPG7.0T2S0k.jTKSGu7uhMMoTaKn4zMMhJbxhReONAa');
INSERT INTO user (enabled, created_by, created_date, id, last_modified_by, last_modified_date, email, full_name, password) VALUES (true, null, '2024-06-18 15:20:56.203965', 4, null, '2024-06-18 15:20:56.203965', 'seller1@example.com', 'seller', '$2a$10$NKfxB1euNxnPG7.0T2S0k.jTKSGu7uhMMoTaKn4zMMhJbxhReONAa');

INSERT INTO seller (id) VALUES (3);
INSERT INTO buyer (id) VALUES (2);

INSERT INTO users_roles (id, role_id, user_id) VALUES (1, 1, 1);
INSERT INTO users_roles (id, role_id, user_id) VALUES (2, 4, 2);
INSERT INTO users_roles (id, role_id, user_id) VALUES (3, 3, 3);
INSERT INTO users_roles (id, role_id, user_id) VALUES (4, 3, 4);

INSERT INTO product (id, name, description, price, brand, product_size, color, material, status, in_stock, posted_date, seller_id, times_bought)
VALUES(1, 'Product 1', 'Description for Product 1', 19.99, 'Brand A', 'L', 'Red', 'Cotton', 'IN_REVIEW', 100, '2023-06-01', 3, 0);


INSERT INTO product_photo (id, name, image_url, image_id, product_id) VALUES (1, 'sketchers-women-black', 'http://res.cloudinary.com/dmyuqmm5o/image/upload/v1718846676/v17hahycwvenzrz0tfcf.png', 'v17hahycwvenzrz0tfcf', 1);

INSERT INTO category (id, name, parent_id) VALUES (1, 'Electronics', null);
INSERT INTO category (id, name, parent_id) VALUES (2, 'Clothing', null);
INSERT INTO category (id, name, parent_id) VALUES (3, 'Books', null);
INSERT INTO category (id, name, parent_id) VALUES (4, 'Laptop', 1);
INSERT INTO category (id, name, parent_id) VALUES (5, 'Iphone', 1);
INSERT INTO category (id, name, parent_id) VALUES (6, 'Fiction',2);

INSERT INTO product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO product_category (product_id, category_id) VALUES (1, 4);


