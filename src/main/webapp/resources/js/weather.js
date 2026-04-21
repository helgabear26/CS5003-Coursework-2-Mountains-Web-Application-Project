const WEATHER_UPDATE_INTERVAL = 60000;

document.addEventListener("DOMContentLoaded", function () {
    setInterval(function () {
        const form = document.getElementById("weatherForm");

        if (form) {
            const event = new Event("submit", { cancelable: true });
            form.dispatchEvent(event);
        }
    }, WEATHER_UPDATE_INTERVAL);
});