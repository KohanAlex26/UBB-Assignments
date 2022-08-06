import matplotlib.pyplot as plt
import numpy as np
from matplotlib.animation import FuncAnimation


def f(x, y):

    return (x - 3.14) ** 2 + (y - 2.72) ** 2 + np.sin(3 * x + 1.41) + np.sin(4 * y - 1.73)


if __name__ == "__main__":
    x, y = np.array(np.meshgrid(np.linspace(0, 5, 100), np.linspace(0, 5, 100)))
    z = f(x, y)

    x_min = x.ravel()[z.argmin()]
    y_min = y.ravel()[z.argmin()]

    c1 = c2 = 0.1
    w = 0.8

    particle_count = 25
    particle_positions = np.random.rand(2, particle_count) * 5
    particle_velocities = np.random.randn(2, particle_count) * 0.1

    particle_best = particle_positions
    particle_best_f = f(particle_positions[0], particle_positions[1])
    global_best = particle_best[:, particle_best_f.argmin()]
    global_best_f = np.min(particle_best_f)


    def update():
        global particle_velocities, particle_positions, particle_best, particle_best_f, global_best, global_best_f
        r1, r2 = np.random.rand(2)
        particle_velocities = w * particle_velocities + c1 * r1 * (particle_best - particle_positions) + c2 * r2 * (global_best.reshape(-1, 1) - particle_positions)
        particle_positions = particle_positions + particle_velocities
        obj = f(particle_positions[0], particle_positions[1])
        particle_best[:, (particle_best_f >= obj)] = particle_positions[:, (particle_best_f >= obj)]
        particle_best_f = np.min(np.array([particle_best_f, obj]), axis=0)
        global_best = particle_best[:, particle_best_f.argmin()]
        global_best_f = np.min(particle_best_f)


    fig, ax = plt.subplots(figsize=(8, 6))
    fig.set_tight_layout(True)
    img = ax.imshow(z, extent=[0, 5, 0, 5], origin='lower', cmap='viridis', alpha=0.5)
    fig.colorbar(img, ax=ax)
    ax.plot([x_min], [y_min], marker='x', markersize=5, color='white')
    contours = ax.contour(x, y, z, 10, colors='black', alpha=0.4)
    ax.clabel(contours, inline=True, fontsize=8, fmt='%.0f')
    plot_particle_best = ax.scatter(particle_best[0], particle_best[1], marker='o', color='black', alpha=0.5)
    plot_particle_positions = ax.scatter(particle_positions[0], particle_positions[1], marker='o', color='blue', alpha=0.5)
    plot_particle_velocities = ax.quiver(particle_positions[0], particle_positions[1], particle_velocities[0], particle_velocities[1], color='blue', width=0.005, angles='xy', scale_units='xy', scale=1)
    plot_global_best = plt.scatter([global_best[0]], [global_best[1]], marker='*', s=100, color='black', alpha=0.4)
    ax.set_xlim([0, 5])
    ax.set_ylim([0, 5])


    def animate(idx):
        title = 'Iteration {:02d}'.format(idx + 1)
        update()
        ax.set_title(title)
        plot_particle_best.set_offsets(particle_best.T)
        plot_particle_positions.set_offsets(particle_positions.T)
        plot_particle_velocities.set_offsets(particle_positions.T)
        plot_particle_velocities.set_UVC(particle_velocities[0], particle_velocities[1])
        plot_global_best.set_offsets(global_best.reshape(1, -1))
        return ax, plot_particle_best, plot_particle_positions, plot_particle_velocities, plot_global_best


    anim = FuncAnimation(fig, animate, frames=10, interval=500, blit=False, repeat=True)
    anim.save('particle_swarm_optimization.gif', dpi=300, writer='pillow')

    print('PSO found best solution at f({})={}'.format(global_best, global_best_f))
    print('Global optimum at f({})={}'.format([x_min, y_min], f(x_min, y_min)))
