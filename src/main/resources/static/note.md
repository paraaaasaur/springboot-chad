- Spring Boot searches the following directories for static resources in top-down fashion:

  `src/main/resources`
    1. `/META-INF/resources`
    2. `/resources`
    3. ⭐ `/static`
        - In this case, js/css/html by convention can be put under
            - js `src/main/resources/static/js`
            - css `src/main/resources/static/css`
                - bootstrap `src/main/resources/static/css/bootstrap.min.css`
            - html `src/main/resources/static/html`
    4. ⭐ `/public`