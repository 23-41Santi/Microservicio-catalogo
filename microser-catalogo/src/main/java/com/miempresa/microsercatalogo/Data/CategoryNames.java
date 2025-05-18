package com.miempresa.microsercatalogo.Data;

public enum CategoryNames {
    // Constantes
    LAPTOP   ("LAP",  "Portátiles",  "Computadores portátiles"),
    TABLET   ("TAB",  "Tabletas",    "Dispositivos táctiles con batería"),
    PHONE    ("PHN",  "Smartphones", "Teléfonos inteligentes"),
    ACCESSORY("ACC",  "Accesorios",  "Periféricos y componentes");

    // Campos “inmutables”
    private final String code;
    private final String displayName;
    private final String description;

    // Constructor privado (Java 24 no cambia esta mecánica)
    CategoryNames(String code, String displayName, String description) {
        this.code = code;
        this.displayName = displayName;
        this.description = description;
    }

    // Accesores públicos
    public String code()        { return code; }
    public String displayName() { return displayName; }
    public String description() { return description;}
}
