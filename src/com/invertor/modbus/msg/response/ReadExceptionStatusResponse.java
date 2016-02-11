package com.invertor.modbus.msg.response;

import com.invertor.modbus.exception.ModbusNumberException;
import com.invertor.modbus.msg.base.ModbusResponse;
import com.invertor.modbus.net.stream.base.ModbusInputStream;
import com.invertor.modbus.net.stream.base.ModbusOutputStream;
import com.invertor.modbus.utils.ModbusFunctionCode;

import java.io.IOException;

/**
 * Copyright (c) 2015-2016 JSC Invertor
 * [http://www.sbp-invertor.ru]
 * <p/>
 * This file is part of JLibModbus.
 * <p/>
 * JLibModbus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Authors: Vladislav Y. Kochedykov, software engineer.
 * email: vladislav.kochedykov@gmail.com
 */
final public class ReadExceptionStatusResponse extends ModbusResponse {

    private int exceptionStatus = 0;

    public ReadExceptionStatusResponse(int serverAddress) throws ModbusNumberException {
        super(serverAddress);
    }

    public int getExceptionStatus() {
        return exceptionStatus;
    }

    public void setExceptionStatus(int exceptionStatus) {
        this.exceptionStatus = (byte) exceptionStatus & 0xff;
    }

    @Override
    protected void readResponse(ModbusInputStream fifo) throws IOException {
        setExceptionStatus(fifo.read());
    }

    @Override
    protected void writeResponse(ModbusOutputStream fifo) throws IOException {
        fifo.write(getExceptionStatus());
    }

    @Override
    protected int responseSize() {
        return 1;
    }

    @Override
    public int getFunction() {
        return ModbusFunctionCode.READ_EXCEPTION_STATUS.toInt();
    }
}
