package com.company.buildings.logic;

/**
 * This is the House class file that extends Building.
 */
public class House extends Building {
    private String mOwner;
    private boolean mPool;

    House(int length, int width, int lotLength, int lotWidth) {
        super(length,width,lotLength,lotWidth);
    }

    House(int length, int width, int lotLength, int lotWidth, String owner) {
        super(length,width,lotLength,lotWidth);
        this.mOwner = owner;
    }

    House(int length, int width, int lotLength, int lotWidth, String owner, boolean pool) {
        super(length,width,lotLength,lotWidth);
        this.mOwner = owner;
        this.mPool = pool;
    }

    public String getOwner() {
        return mOwner;
    }

    public boolean hasPool() {
        return mPool;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public void setPool(boolean pool) {
        mPool = pool;
    }

    public String toString() {
        StringBuilder houseString = new StringBuilder("Owner: ");
        if (mOwner != null) {houseString.append(mOwner);}

        if (calcLotArea() > calcBuildingArea()) {houseString.append("; has a big open space");}

        return houseString.toString();
    }

    public boolean equals(Object obj) {
        // Совпадает ли ссылка на сравниваемый объект с текущим экземпляром
        if (this == obj) return true;

        if (!(obj instanceof House)) return false;
        // obj преобразуется в объект House
        House otherHouse = (House) obj;

        return super.calcBuildingArea() == otherHouse.calcBuildingArea() && this.mPool == otherHouse.mPool;
    }
}
