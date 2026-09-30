ALTER TABLE categories DROP CONSTRAINT uq_categories_name;
CREATE UNIQUE INDEX uq_categories_name_lower ON categories (LOWER(name));