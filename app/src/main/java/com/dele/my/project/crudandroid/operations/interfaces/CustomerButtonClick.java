package com.dele.my.project.crudandroid.operations.interfaces;

import com.dele.my.project.crudandroid.operations.pojo.Customers;

/**
 * @author deele
 * @project CrudAndroid
 * @day Tuesday
 * @philosophy Quality must be enforced, otherwise it won't happen. We programmers must be required to write tests, otherwise we won't do it!
 * <p>
 * ------
 * Tip: Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.
 * ------
 * copied ****
 * @since 09/08/2022 1:01 PM
 */
public interface CustomerButtonClick {
    void onButtonClickListener(int position, Customers customer, String action);
}
