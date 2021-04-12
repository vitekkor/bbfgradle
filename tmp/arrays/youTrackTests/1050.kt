// Original bug: KT-13108

// Third party code - I can't change
interface WebDriver
interface HasInputDevices
class RemoteWebDriver : WebDriver, HasInputDevices
class EventFiringWebDriver : WebDriver, HasInputDevices

// My code
class WebDriverClient(driver: RemoteWebDriver)
