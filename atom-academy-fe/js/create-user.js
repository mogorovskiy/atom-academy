async function createUser(event) {
    event.preventDefault();

    const submitBtn = event.target.querySelector('.btn-auth');
    const originalText = submitBtn.innerText;

    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    try {
        submitBtn.innerText = 'INITIALIZING...';
        submitBtn.disabled = true;

        const response = await fetch(`${window.CONFIG.API_BASE_URL}/auth/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                email: email,
                password: password
            })
        });

        const data = await response.json();

        if (response.ok) {
            // 3. Успех: перенаправляем на логин через 1.5 секунды
            submitBtn.style.borderColor = '#00ff66';
            submitBtn.innerText = 'ACCESS_GRANTED';

            setTimeout(() => {
                window.location.href = 'login-page.html';
            }, 1500);
        } else {
            // 4. Ошибка от сервера (например, такой email уже есть)
            throw new Error(data.message || 'Registration failed');
        }
    } catch (e) {
        console.error("Ошибка:", e);
        submitBtn.innerText = 'ERROR: REJECTED';
        submitBtn.style.borderColor = '#ff4444';

        // Возвращаем кнопку в исходное состояние через 2 секунды
        setTimeout(() => {
            submitBtn.innerText = originalText;
            submitBtn.disabled = false;
            submitBtn.style.borderColor = '#00ff66';
        }, 2000);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const regForm = document.getElementById('register-form');
    if (regForm) {
        regForm.addEventListener('submit', createUser);
    }
});