async function loginUser(event) {
    event.preventDefault();

    const submitBtn = event.target.querySelector('.btn-auth');

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    debugger;

    try {
        submitBtn.innerText = 'INITIALIZING...';
        submitBtn.disabled = true;

        const response = await fetch(`${window.CONFIG.API_BASE_URL}/auth/login`, {
            credentials: 'include',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                password: password,
            })
        });

        const data = await response.json();

        if (response.ok) {
            // Сохраняем данные в браузер
            localStorage.setItem('userName', data.userName);
            localStorage.setItem('userRole', data.userRole);

            submitBtn.innerText = 'ACCESS_GRANTED';
            setTimeout(() => {
                window.location.href = '../home-page.html';
            }, 500);
        } else {
            // 4. Ошибка от сервера (например, такой email уже есть)
            throw new Error(data.message || 'Login failed.');
        }
    } catch (e) {
        submitBtn.innerText = "ERROR: REJECTED"; // Это ты уже сделал
        submitBtn.classList.add('btn-error');    // А ВОТ ЭТО НУЖНО ДОБАВИТЬ!

        setTimeout(() => {
            submitBtn.innerText = "Инициализировать";
            submitBtn.classList.remove('btn-error');
        }, 2000);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const regForm = document.getElementById('login-form');
    if (regForm) {
        regForm.addEventListener('submit', loginUser);
    }
});