async function loadCourses() {
    try {
        const response = await fetch('http://localhost:8080/api/courses');
        if (!response.ok) throw new Error('Ошибка сети');

        const courses = await response.json();
        const row = document.getElementById('courses-row');
        row.innerHTML = '';

        courses.forEach(course => {
            const col = document.createElement('div');
            col.className = 'col-12 mb-3';

            debugger;

            const badgeColor = {
                'EASY': 'bg-success',
                'MEDIUM': 'bg-warning text-dark',
                'HARD': 'bg-danger'
            }[course.complexity] || 'bg-primary';

            col.innerHTML = `
                <div class="card bg-dark text-light border-secondary h-100 shadow">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h5 class="card-title">${course.title}</h5>
                            <span class="badge ${badgeColor}">${course.complexity}</span>
                        </div>
                        <p class="card-text text-light small mt-2">${course.description}</p>
                        <p class="card-text"><small>Автор: ${course.authorName}</small></p>
                        <a href="lessons.html?id=${course.id}" class="btn btn-sm btn-neon-purple w-100 mt-2">Смотреть вопросы</a>
                    </div>
                </div>
            `;
            row.appendChild(col);
        });
    } catch (error) {
        console.error('Ошибка:', error);
        document.getElementById('courses-row').innerHTML = '<p class="text-danger text-center">Не удалось загрузить курсы :(</p>';
    }
}

document.addEventListener('DOMContentLoaded', loadCourses);