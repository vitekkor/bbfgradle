// Original bug: KT-15050

// Disposing of the environment is unsafe in production then parallel builds are enabled, but turning it off universally
// breaks a lot of tests, therefore it is disabled for production and enabled for tests
