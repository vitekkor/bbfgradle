// Original bug: KT-15733

// Backend Errors: 
// ================
// Error at ab.kt(47,53): The 'foo' invocation is a part of inline cycle
// Error at ab.kt(116,126): The 'bar' invocation is a part of inline cycle
// Error at ab.kt(116,126): The 'bar' invocation is a part of inline cycle
// Error at ab.kt(47,53): The 'foo' invocation is a part of inline cycle
// Error at ab.kt(116,126): The 'bar' invocation is a part of inline cycle
// Error at ab.kt(47,53): The 'foo' invocation is a part of inline cycle
// ================
