# Trimming bound data with @InitBinder

- Spring MVC annotation that marks a method
- Allows you to customize the request data binding process
- Applies to all routing methods under that controller class
- Paired with `WebDataBinder` param to **register customizers** to tweak incoming data from `@ModelAttribute` or `@RequestParam`
    - e.g. `StringTrimmerEditor`: used to trim leading and trailing whitespaces

        ```java
        	@InitBinder
        	public void initBinder(WebDataBinder dataBinder) {
        		var ste = new StringTrimmerEditor(true);
        		dataBinder.registerCustomEditor(String.class, ste);
        	}
        ```