async function loadQuestions() {
    const urlParams = new URLSearchParams(window.location.search);
    const courseId = urlParams.get('id');

    if (!courseId) {
        document.getElementById('questions-list').innerHTML =
            '<div class="alert alert-danger">Курс не указан!</div>';
        return;
    }

    try {
        const courseRes = await fetch(`${window.CONFIG.API_BASE_URL}/courses/${courseId}`);
        const courseData = await courseRes.json();

        // Заполняем текстовые блоки
        document.getElementById('course-title').innerText = courseData.title;

        // 2. Загружаем сами вопросы
        const response = await fetch(`${window.CONFIG.API_BASE_URL}/courses/${courseId}/questions`);
        const questions = await response.json();
        const container = document.getElementById('questions-list');
        container.innerHTML = '';

        if (questions.length === 0) {
            container.innerHTML = '<h4 class="text-center text-muted mt-5">В этом курсе пока нет уроков.</h4>';
            return;
        }

        questions.forEach(question => {
            const div = document.createElement('div');
            div.className = 'course-card-link fade-in mb-4 w-100';
            div.style.maxWidth = "800px";

            div.innerHTML = `
                <div class="liquid-glass d-flex justify-content-between align-items-center" style="cursor: pointer;">
                    <div style="flex: 1;">
                        <h5 class="mb-1">${question.question}</h5>
                        <p class="small mb-0">Нажмите, чтобы увидеть ответ</p>
                    </div>
                    <div class="btn-minimal ms-3">Смотреть</div>
                </div>
            `;

            div.addEventListener('click', async () => {
                try {
                    // Делаем запрос за полным объектом (с ответом)
                    const response = await fetch(`${window.CONFIG.API_BASE_URL}/questions/${question.id}`, {
                        // Не забудь credentials, чтобы отправилась кука сессии!
                        credentials: 'include'
                    });

                    if (response.status === 401) {
                        alert("Пожалуйста, войдите в аккаунт, чтобы увидеть ответ!");
                        window.location.href = "../public/authorization/login-page.html";
                        return;
                    }

                    const fullQuestion = await response.json();

                    document.getElementById('modalTitle').innerText = fullQuestion.question;
                    document.getElementById('modalBody').innerHTML = marked.parse(fullQuestion.answer);
                    new bootstrap.Modal(document.getElementById('answerModal')).show();

                } catch (e) {
                    console.error("Ошибка доступа:", e);
                }
            });

            container.appendChild(div);
        });
    } catch (e) {
        console.error("Ошибка:", e);
        document.getElementById('questions-list').innerHTML = '<p class="text-danger">Ошибка загрузки.</p>';
    }
}

loadQuestions();