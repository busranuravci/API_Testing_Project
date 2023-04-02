const assert = require('chai').assert;

// Test sınıfı
describe('MyTestClass', function() {

  // Test yöntemi
  it('should return true when the value is true', function() {
    const value = true;
    assert.isTrue(value);
  });

  // Başka bir test yöntemi
  it('should return false when the value is false', function() {
    const value = false;
    assert.isFalse(value);
  });

});