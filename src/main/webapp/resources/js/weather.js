const WEATHER_UPDATE_INTERVAL = 60000;
let weatherUpdateTimer = null;

document.addEventListener('DOMContentLoaded', function () {

    console.log('Weather page loaded');

    setTimeout(updateWeatherNow, 500);

    startWeatherAutoUpdate();
});

function startWeatherAutoUpdate() {

    if (weatherUpdateTimer !== null) {
        clearInterval(weatherUpdateTimer);
    }

    weatherUpdateTimer = setInterval(updateWeatherNow, WEATHER_UPDATE_INTERVAL);

    console.log('Auto-update started');
}

function stopWeatherAutoUpdate() {
    if (weatherUpdateTimer !== null) {
        clearInterval(weatherUpdateTimer);
        weatherUpdateTimer = null;
    }
}

function updateWeatherNow() {

    var updateBtn = document.querySelector('.updateBtn');

    if (updateBtn) {
        updateBtn.click();
    } else {
        console.error('Update button not found');
    }
}

function showLoadingSpinner() {

    var spinner = document.getElementById('loadingSpinner');

    if (!spinner) {
        spinner = document.createElement('div');
        spinner.id = 'loadingSpinner';
        spinner.innerHTML = '<span>Updating weather...</span>';

        var weatherPanel = document.querySelector('.weather-panel');

        if (weatherPanel) {
            weatherPanel.parentNode.insertBefore(spinner, weatherPanel);
        }
    }

    spinner.style.display = 'block';
}

function hideLoadingSpinner() {

    var spinner = document.getElementById('loadingSpinner');

    if (spinner) {
        setTimeout(function () {
            spinner.style.display = 'none';
        }, 300);
    }
}

function manualRefreshWeather() {
    updateWeatherNow();
}

document.addEventListener('visibilitychange', function () {
    if (document.hidden) {
        stopWeatherAutoUpdate();
    } else {
        startWeatherAutoUpdate();
    }
});

window.addEventListener('beforeunload', function () {
    stopWeatherAutoUpdate();
});