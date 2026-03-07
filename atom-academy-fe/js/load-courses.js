async function loadCourses() {
    const container = document.getElementById('courses-roadmap');
    container.innerHTML = '';

    try {
        const response = await fetch(`${window.CONFIG.API_BASE_URL}/courses`);
        const courses = await response.json();

        courses.forEach((course, index) => {
            // Оборачиваем карточку в ссылку
            const link = document.createElement('a');
            link.href = `../private/questions.html?id=${course.id}`;
            link.className = 'course-card-link fade-in';

            link.innerHTML = `
                <div class="liquid-glass">
                    <h5 class="text-success">${course.title}</h5>
                    <p class="small text-secondary mb-0">${course.description}</p>
                </div>
            `;
            container.appendChild(link);

            // Добавляем линию
            if (index < courses.length - 1) {
                const line = document.createElement('div');
                line.className = 'roadmap-line';
                container.appendChild(line);
            }
        });
    } catch (e) {
        container.innerHTML = '<p class="text-danger text-center">Ошибка загрузки.</p>';
    }
}

document.addEventListener('DOMContentLoaded', loadCourses);