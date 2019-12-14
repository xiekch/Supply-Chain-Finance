
$(function () {
    var validator = {
        form: {
            username: {
                status: false,
                error: 'User\'s name can be 5~15 alphanumeric or underscore characters',
                isValid: function (data) {
                    return this.status = /^[\w\u4e00-\u9fa5]{5,15}$/.test(data);
                }
            },
            password: {
                status: false,
                error: 'Password can be 5~15 alphanumeric or underscore characters',
                isValid: function (data) {
                    return this.status = /^[\w\u4e00-\u9fa5]{5,15}$/.test(data);
                }
            }
        },

        isValid: function () {
            return this.form.username.status && this.form.password.status;
        },

        getError: function (filedName) {
            return this.form[filedName].error;
        }
    }

    //check valid when blurs
    $('.field').blur(function () {
        if (validator.form[$(this).attr('id')].isValid($(this).val())) {
            $(this).parent().find('.error').text('');
        } else {
            $(this).parent().find('.error').text(validator.getError(this.id));
        }
    });

    $('#submit').click(function () {
        $('.field').blur();
        if (!validator.isValid()) return false;
    });

    $('#reset').click(function () {
        $('.field').val('');
        $('.error').text('');
    });
});