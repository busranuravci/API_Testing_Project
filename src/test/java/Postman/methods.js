const assert = require('chai').assert;

Postman generally use Chai Assertion library (javascript framework)

pm.test("Test Name", function ()   //normal function
{
    //assertion;
}
);

pm.test("Test Name", () =>   //arrow function  //most popular function
{
   //assertion;
}
);

GET

pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

POST

pm.test("Status code is 201", function () {
    pm.response.to.have.status(201);
});

pm.test("Successful POST request", function () {
    pm.expect(pm.response.code).to.be.oneOf([201, 202]);
});

DELETE

pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

to negative test

pm.test("Status code is 404", function () {
    pm.response.to.have.status(404);
});

//status code
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

//get request
pm.test("Successful Get request", () =>{
    pm.expect(pm.response.code).to.be.oneOf([200,201]);
});

//testing headers presence
pm.test("Content-Type header is present", () => {
    pm.response.to.have.header("Content-Type");
});

//content type
pm.test("Content-Type header is application/json", () => {
    pm.expect(pm.response.headers.get('Content-Type')).to.eql('application/json; charset=utf-8');
});

//time
pm.test("Response time is less than 200ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(200);
});

//RESPONSE BODY

const jsonData = pm.response.json();
pm.test("test data type of the response", () =>{
    pm.expect(jsonData).to.be.an("object");
    pm.expect(jsonData.id).to.be.an("number");
    pm.expect(jsonData.name).to.be.an("string");
    pm.expect(jsonData.describtion).to.be.an("object");
    pm.expect(jsonData.price).to.be.an("number");
});

//array properties
pm.test("test array properties", () =>{
    pm.expect(jsonData.courses).to.include("Java");
    pm.expect(jsonData.courses).to.have.members("Java","Selenium","API");
})




