SELECT * FROM users;

-- SELECT * FROM users;
--SELECT column_1, column_2 ...
--FROM table_name;
--id, first_name, last_name, age, balance, phone, email, country이 컬럼들 중 어떤 컬럼 가지고오고싶나?
SELECT first_name, age
FROM users;
SELECT first_name, last_name, age
FROM users;
-- name, age, balance, phone SELECT
SELECT first_name, age, balance, phone
FROM users;

--DISTINCT : 중복없이 조회할 때 사용
SELECT DISTINCT country
FROM users;

SELECT DISTINCT first_name
FROM users;
--DISTINCT에 두개의 컬럼을 작성하면? -> 두 값이 조합된 걸 기준으로 추력. 완전 동명이인만!!!!
SELECT DISTINCT first_name, last_name
FROM users;

--SELECT ORDER BY
SELECT first_name, age, balance
FROM users
ORDER BY first_name;

SELECT last_name, balance
FROM users
ORDER BY balance DESC;

--ORDER BY 에 두가지 이상의 칼럼을 지정하면?
SELECT first_name, last_name
FROM users
ORDER BY balance DESC, age DESC;

--WHERE: 조건을 덧붙여 조회
-- =, !=, < ,> , >= <= 정도는 인식
SELECT *
FROM users
WHERE age >= 30;

SELECT *
FROM users
WHERE age < 30;

SELECT *
FROM users
WHERE age >= 40 AND age < 50;

-- 30미만 60이상
SELECT *
FROM users
WHERE age < 30 OR age >= 60;

--WHERE 문자열 LIKE -> 문자열을 일치시키는데 특정한 패턴을 포함하거나 포함하지 않는다. (유사)
--EMAIN 이 네이버.컴 인 계정만 조회
SELECT id, first_name, email
FROM users
WHERE email LIKE '%naver.com';
-- %기호를 사용하면, 0개 이상의 문자와 일치한다고 가정한다.  %가 있는자리에는 뭐가와도 걍 그거@ㅉ

SELECT id, first_name, phone
FROM users
WHERE phone LIKE '010%';

SELECT id, first_name, phone
FROM users
WHERE phone NOT LIKE '010%';

--between, in 쓰면 좀 더 편하긴 하지만 AND, OR로 대체가능, 노션에서 내용 확인가능@@