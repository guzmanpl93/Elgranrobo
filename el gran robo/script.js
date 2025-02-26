// script.js

// Function to log a message to the console
/*function logMessage(message) {
    console.log(message);
}

// Example usage of the logMessage function
logMessage("Hello, world!");*/
document.addEventListener("DOMContentLoaded", function() {
    const miembros = [
        {
            nombre: "Guzmán",
            rol: "admin y desarrollador",
            descripcion: "Me encanta la programación y la tecnología."
        },
        {
            nombre: "Javi",
            rol: "admin y desarrollador",
            descripcion: "Me encanta la programación y la tecnología."
        },
        {
            nombre: "Andrea",
            rol: "admin y diseñadora",
            descripcion: "Me encanta la programación y la tecnología."
        }
    ];

    document.querySelectorAll("#equipo .miembro").forEach(miembroDiv => {
        const nombreElemento = miembroDiv.querySelector("h2");
        const parrafo = miembroDiv.querySelector("p");
        const miembroInfo = miembros.find(m => m.nombre === nombreElemento.textContent);
        if (miembroInfo){
            parrafo.textContent = miembroInfo.descripcion;
        }
    });

    document.querySelectorAll("#equipo .miembro img").forEach(img => {
        img.addEventListener("mouseover", () => {
            img.computedStyleMap.transform = "scale(1.1)";
            img.computedStyleMap.transition = "0.3s ease-in-out";
        });
        img.addEventListener("mouseout", () => {
            img.computedStyleMap.transform = "scale(1)";
        });
    });
});