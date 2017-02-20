function changeLocale(locale) {
	document.cookie = 'locale-cookie=' + locale;
}

function calcTableIdx($table) {
	$table.find('td[id=index]').each(function (idx) {
		$(this).text(idx + 1);
	});
}

function bindFormSubmit($dialog, $table, $row) {
	var $form = $dialog.find('form');
	$form.on('submit', function (e) {
		e.preventDefault();
		$.ajax({
			url: $form.attr('action'),
			type: $form.attr('method'),
			data: $form.serialize(),
			success: function (response) {
				if ($(response).find('.has-error').addBack('.has-error').length) {
					$form.replaceWith(response);
					bindFormSubmit($dialog, $table, $row);
				} else {
					$dialog.modal('hide');
					if ($row) {
						$row.replaceWith(response);
					} else {
						$table.find('tbody').prepend(response);
					}
					calcTableIdx($table);
				}
			}
		});
	});
}