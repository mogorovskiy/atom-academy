async function loadQuestions() {
    const urlParams = new URLSearchParams(window.location.search);
    const courseId = urlParams.get('id');

    debugger;

    if (!courseId) {
        document.getElementById('questions-list').innerHTML =
            '<div class="alert alert-danger">Курс не указан! Вернитесь на главную.</div>';
        return;
    }

    try {
        const courseRes = await fetch(CONFIG.API_BASE_URL + `/courses/${courseId}`);
        const courseData = await courseRes.json();
        document.getElementById('course-title').innerText = courseData.title;

        const response = await fetch(CONFIG.API_BASE_URL + `/courses/${courseId}/questions`);
        const questions = await response.json();

        const container = document.getElementById('questions-list');
        container.innerHTML = '';

        if (questions.length === 0) {
            container.innerHTML = '<h4 class="text-center text-muted mt-5">В этом курсе пока нет уроков.</h4>';
            return;
        }

        questions.forEach(question => {
            const div = document.createElement('div');
            div.className = 'course-card-link fade-in mb-4';

            div.innerHTML = `
    <div class="liquid-glass d-flex justify-content-between align-items-center">
        <div style="flex: 1;">
            <h5 class="mb-1">${question.question}</h5>
            <p class="small mb-0">Нажмите, чтобы открыть ответ</p>
        </div>
    </div>
`;

// Весь блок становится кликабельным
            div.addEventListener('click', () => {
                document.getElementById('modalTitle').innerText = question.question;
                document.getElementById('modalBody').innerHTML = marked.parse(question.answer || 'Ответ отсутствует');
                new bootstrap.Modal(document.getElementById('answerModal')).show();
            });
            container.appendChild(div);
        })
    } catch (e) {
        console.error("Ошибка:", e);
        document.getElementById('questions-list').innerHTML =
            '<div class="alert alert-danger">Не удалось загрузить уроки. Проверьте бэкенд.</div>';
    }
}

loadQuestions();