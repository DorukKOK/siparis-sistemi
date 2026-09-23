CREATE TABLE catagories(
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);
CREATE TABLE products(
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price NUMERIC(10,2) NOT NULL,
                         stock_quantity INTEGER NOT NULL,
                         category_id BIGINT NOT NULL,
                         CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES catagories(id)
)