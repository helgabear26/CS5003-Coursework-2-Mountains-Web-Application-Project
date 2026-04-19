const WEATHER_UPDATE_INTERVAL= 60000;
let weatherUpdateTimer = null;

document.addEventListener('DOMContentLoaded', function () {
    console.log('Weather page loaded through auto-refresh (every 60 seconds)');

    updateWeatherNow();

    startWeatherAutoUpdate();
});

function startWeatherAutoUpdate() {

    if (weatherUpdateTimer !== null) {
        clearInterval(weatherUpdateTimer);
    }

    weatherUpdateTimer = setInterval(function () {
        updateWeatherNow();
    }, WEATHER_UPDATE_INTERVAL);

    console.log('Weather auto-refresh timer started');
}

function stopWeatherAutoUpdate() {
    if (weatherUpdateTimer !== null) {
        clearInterval(weatherUpdateTimer);
        weatherUpdateTimer = null;
        console.log('Weather auto-refresh timer stopped');
    }
}

function updateWeatherNow() {
    var updateBtn = document.getElementById('weatherForm:updateBtn');

    if (updateBtn) {
        console.log('Fetching weather (' + new Date().toLocaleDateString() + ')');
        updateBtn.click();
    } else {
        console.error('Update Button unavailable.');
    }
}

function showLoadingSpinner() {
    var spinner = document.getElementById('loadingSpinner');

    if (!spinner) {

        spinner = document.createElement('div');
        spinner.id = 'loadingSpinner';
        spinner.innerHTML = '<span> Updating weather</span>';

        var weatherPanel = document.getElementById('weatherForm:weatherPanel');
        if (weatherPanel) {
            weatherPanel.parentNode.insertBefore(spinner, weatherPanel);
        }
    }

    spinner.classList.add('show');
    spinner.style.display = 'block';
    console.log('Loading spinner shown')
}

function hideLoadingSpinner() {
    var spinner = document.getElementById('loadingSpinner');

    if(spinner) {
        setTimeout(function() {
            spinner.classList.remove('show');
            spinner.style.display = 'none';
            console.log('Weather updated');
        }, 300);
    }
}

function manualRefreshWeather() {
    console.log('Manual weather refresh triggered');
    updateWeatherNow();
}

document.addEventListener('visibilitychange', function() {
    if (document.hidden) {
        console.log('Page hidden. Pausing weather updates');
        stopWeatherAutoUpdate();
    } else {
        console.log('Page visible. Resuming Weather Updates');
        startWeatherAutoUpdate();
    }
});

window.addEventListener('beforeunload', function() {
    stopWeatherAutoUpdate();
});