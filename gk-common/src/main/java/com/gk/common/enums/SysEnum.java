package com.gk.common.enums;

public interface SysEnum {

    enum sAdmin {
        YES(1),
        NO(0);

        private int value;

        sAdmin(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    enum MenuTypeEnum {
        /**
         * 菜单
         */
        MENU(0),
        /**
         * 按钮
         */
        BUTTON(1);

        private int value;

        MenuTypeEnum(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
