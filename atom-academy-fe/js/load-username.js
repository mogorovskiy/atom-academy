document.addEventListener('DOMContentLoaded', () => {
    const authSection = document.getElementById('auth-section');
    const userName = localStorage.getItem('userName');

    if (userName && authSection) {
        authSection.innerHTML = `
            <div class="d-flex align-items-center">
                <!-- Футуристичный бейдж -->
                <div class="user-profile-badge">
                    <div class="status-dot"></div>
                    <span class="user-name-text">
                        ${userName.toUpperCase()}
                    </span>
                </div>
                
                <!-- Кнопка выхода с красным акцентом -->
                <a href="#" class="btn-minimal btn-logout" id="logout-btn">
                    Disconnect
                </a>
            </div>
        `;

        document.getElementById('logout-btn').addEventListener('click', (e) => {
            e.preventDefault();
            localStorage.clear();
            window.location.reload();
        });
    }
});