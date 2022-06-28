package com.geekbrains.lesson4;

public class Cat {
    private String name;
    private boolean isVaccinated;

    public Cat(String name, boolean isVaccinated) {
        this.name = name;
        this.isVaccinated = isVaccinated;
    }

    public String getName() {
        return name;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }
}
